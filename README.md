# ui-math [![GitHub Actions status |pink-gorilla/ui-math](https://github.com/pink-gorilla/ui-math/workflows/CI/badge.svg)](https://github.com/pink-gorilla/ui-math/actions?workflow=CI)[![Clojars Project](https://img.shields.io/clojars/v/org.pinkgorilla/ui-math.svg)](https://clojars.org/org.pinkgorilla/ui-math)

mathjax vizualisation
This is in a separate extension because mathjax is 2 MB and loading takes 2 additional seconds 

## Demo

```
clojure -X:goldly
```

Navigate your webbrowser to port 8000. 
Snippets are in `running systems` / `snippet-registry`

## In Goldly as a ui extension

In deps.edn add ui-math as dependency and add goldly alias

```
:goldly
  {:extra-deps {org.pinkgorilla/goldly {:mvn/version "RELEASE"}
               {org.pinkgorilla/ui-math {:mvn/version "0.0.4"}}
   :exec-fn goldly-server.app/goldly-server-run!
   :exec-args {:profile "watch"
               :config {:goldly {}}}}
```




