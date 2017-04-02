(ns mlclojure.plots
  (:import (org.jzy3d.chart2d Chart2d)
           (org.jzy3d.plot2d.primitives Serie2d Serie2d$Type)
           (org.jzy3d.colors Color)
           (org.jzy3d.ui LookAndFeel)
           (net.miginfocom.swing MigLayout)
           (javax.swing JFrame JPanel BorderFactory)
           (java.awt BorderLayout Component)))

(def ^{:private true} frame (atom nil))

(defn- update-frame [new-frame]
  (compare-and-set! frame nil new-frame))


(defn init! [& {:keys [^String lines ^String columns ^String title]
                :or {lines "[300px]"
                     columns "[500px,grow]"
                     title "Plot"}}]
  (LookAndFeel/apply)
  (doto (JFrame.)
    (.setTitle title)
    (.setLayout (MigLayout. "" lines columns))
    (.pack)
    (update-frame)))


(defn- add-chart!
  [chart]
  (let [layout (BorderLayout.)
        panel (JPanel. layout)
        line-border (BorderFactory/createLineBorder java.awt.Color/WHITE)]
    (doto panel
      (.setBorder line-border)
      (.add (cast Component (.getCanvas chart)) (cast Object BorderLayout/CENTER)))
    (doto @frame
      (.add panel "cell 0 0, grow"))))

(defn- add-coords! [^Serie2d serie coords]
  (doseq [[^Float x ^Float y] coords]
    (.add serie x y))

(defn draw-plot [coords & {:keys [max-x max-y min-y label-x label-y label-serie color]
                           :or {
                                max-x 10.
                                max-y 100.
                                min-y 0.
                                label-x "X"
                                label-y "Y"
                                label-serie "Line"
                                color Color/RED}}]
  (let [chart (Chart2d.)]
    (doto chart
      (.asTimeChart max-x min-y max-y label-x label-y)
      (-> (.getSerie label-serie Serie2d$Type/LINE)
          (.setColor color)
          (add-coords! coords)))
    (add-chart! chart)
    (.pack @frame)
    (.setVisible @frame true))))

