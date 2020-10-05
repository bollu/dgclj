(ns dg)


(defn op* [x y] (list '* x y))
(defn op+ [x y] (list '+ x y))
(defn op- [x y] (list '- x y))

(op* (op+ 'p 1) (op- 'q 2))

(defn symbolic-derivative [f v]
  (cond
    (= f v) 1
    (symbol? f) 0
    ;; from now on, f must be a list.
    (= (first f) '+) (op+ (symbolic-derivative (nth f 1) v)
                          (symbolic-derivative (nth f 2) v))
    (= (first f) '*) (op+ (op* (nth f 2) (symbolic-derivative (nth f 1) v))
                          (op* (nth f 1) (symbolic-derivative (nth f 2) v)))
    :else 0))

(symbolic-derivative 'x 'x)
(symbolic-derivative 'x 'y)

(op* 1 2)

;; R -> R^3
(defn c [t] (list t (* t t) (* t t t)))

;; R^3 -> R
;; (def-fn f [x y z] [])
(defn main [opts]
  (println "Hello, World!"))

