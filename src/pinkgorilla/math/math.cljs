(ns pinkgorilla.math.math
  " MathJax has v2 and v3. v3 is breaking the v2 api.
    es6 modules for the browser not working; this means browser 
    will load mathjax to window/MathJax
    so we ship the compiled mathjax.js bundle
   "
  (:require
   [taoensso.timbre :refer-macros [info warn]]
   [reagent.core :as r]
   [pinkie.jsrender :refer [render-js]]
   ;["pinkgorilla/math/tex-svg-full.js" :as mj]
   ["/pinkgorilla/math/mathinit" :as math]))

;; https://docs.mathjax.org/en/latest/web/configuration.html

(defonce needs-init (atom true))
(defonce math-loaded (r/atom false))

(defn ensure-loaded! []
  (when @needs-init
    (reset! needs-init false)
    (math/mathinit (fn []
                     (info "mathjax is loaded!")
                     (reset! math-loaded true))))
  nil)

#_(defn add-math-css []
    (let [mathjax (.-MathJax js/window)
          sheet (.querySelector js/document "#MJX-CHTML-styles")]
      (when (not sheet)
        (.appendChild (.-head js/document) (.chtmlStylesheet mathjax)))))

(def options
  {:display true ;"process as inline math"
   :em 16 ; em-size in pixels
   :ex 8 ; ex-size in pixels
   :containerWidth (* 8 16); 'width of container in pixels'
   :css false ; output the required CSS rather than the HTML itself
   :fontCache true ; 'whether to use a local font cache or not'
   :dist true ; 'true to use webpacked version, false to use MathJax source files'
   })

(defn render-math [dom-node data-js]
  (let [mathjax (.-MathJax js/window)
        ;options (clj->js options)
        options (.getMetricsFor mathjax dom-node true)]
    (-> (.tex2svgPromise mathjax data-js options) ;
        (.then (fn [math-node]
                 (.appendChild dom-node math-node)
                 #_(add-math-css))))))

(defn ^{:category :ui}
  math-impl
  "displays mathematical formulas"
  [{:keys [data options]}]
  (ensure-loaded!)
  (fn [{:keys [data options]}]
    (if @math-loaded
      [render-js {:f render-math :data data}]
      [:p "math init.."])))

(defn math [spec]
  [math-impl spec])


