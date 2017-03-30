(ns mlclojure.core
  (:gen-class)
  (:require [clojure.core.matrix :as mat]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(mat/set-current-implementation :clatrix)

(def ^{:private true} split-by-space
  #(str/split (str/trim %) #"\s+"))

(def ^{:private true} take-only-necessary
  #(take 7 %))

(defn- number-or-default
  [str default]
  (let [n (read-string str)]
    (if (number? n) n default)))

(def ^{:private true} convert-to-numbers
  #(map
     (fn [str] (number-or-default str 0.)) %))


(defn readfile
  [filename]
  (with-open [reader (io/reader filename)]
    (doall
      (let [line (line-seq reader)]
        (->> line
             (map split-by-space)
             (map take-only-necessary)
             (map convert-to-numbers))))))

(defn- get-columns
  [coll indices]
  (map (partial nth coll) indices))

(defn- max-row
  [matrix]
  (->> matrix
       (mat/transpose)
       (map mat/emax)
       (mat/array)))

(defn- mean-row
  [matrix]
  (let [m (mat/transpose matrix)
        size (count (first m))]
    (->> m
         (map #(reduce + %))
         (map #(/ % size))
         (mat/array))))

(defn- mean-normalize
  [matrix]
  (let [mean-row (mean-row matrix)
        max-row (max-row matrix)]
    (-> matrix
        (mat/sub mean-row)
        (mat/div max-row))))

(defn- prepend-column-value
  [matrix val]
  (->> matrix
       (map (partial cons val))
       (mat/matrix)))

(defn- gradient-descent-step
  [learning-rate feature y theta]
  (let [difference (mat/sub (mat/mmul feature theta) y)]
    (-> feature
        (mat/transpose)
        (mat/mmul difference)
        (mat/mul (/ learning-rate (mat/row-count y)))
        (->>
          (mat/sub theta)))))

(defn- gradient-descent
  [steps learning-rate feature y theta]
  (take steps
        (iterate (partial gradient-descent-step learning-rate feature y)
                 theta)))


(defn -main
  []
  (let [data (readfile "auto-mpg.data.txt")
        y (mat/matrix (map #(get-columns % [0]) data))
        x (mat/matrix (map #(get-columns % (range 1 7)) data))]
    (prn (last
          (gradient-descent 600 0.05
                            (prepend-column-value (mean-normalize x) 1.)
                            y
                            (mat/zero-matrix 7 1))))))
    ;;(mat/pm x)
    ;;(mat/pm (max-row x))
    ;;(mat/pm (mean-row x))
    ;;(mat/pm (mean-normalize x))
    ;;(mat/pm (prepend-column-value x 1.))
    ;;(println y)

