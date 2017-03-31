(ns mlclojure.plots
  (:import (org.jzy3d.chart2d Chart2d)
           (org.jzy3d.plot2d.primitives Serie2d Serie2d$Type)
           (org.jzy3d.colors Color)
           (org.jzy3d.plot3d.primitives ConcurrentLineStrip)))

(defn draw-plot [& {:keys [max-x max-y min-y label-x label-y label-serie color]
                    :or {max-x 10.
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
         (.setColor color)))))

