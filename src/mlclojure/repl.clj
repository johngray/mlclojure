 (ns mlclojure.repl
   (:require [instaparse.core :as insta]))

(def unambiguous-tokenizer
  (insta/parser
    "sentence = token (<whitespace> token)*
     <token> = keyword | !keyword identifier
     whitespace = #'\\s+'
     identifier = #':[a-zA-Z]+'
     keyword = 'print' | 'plot' | 'load'"))