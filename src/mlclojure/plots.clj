(ns mlclojure.plots
  (:import (org.jzy3d.chart2d Chart2d)
           (org.jzy3d.plot2d.primitives Serie2d Serie2d$Type)))

(let [chart (Chart2d.)]
  (doto chart
    (.asTimeChart 5.5 0 100.0 "Iterations" "Cost")
    (doto (.getSerie "Gradient" Serie2d$Type/LINE))))
