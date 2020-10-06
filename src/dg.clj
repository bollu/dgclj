(ns dg)



(defn third [l] (nth l 2))

(defn sym-simpl [f]
  (if (or (symbol? f) (number? f)) f ; then
    (let [op (first f) ; else
            x (second f)
            y (third f)]
        (if 
          (and (number? x) (number? y))
          (eval f) ; then
          f))))

(defn sym* [x y] (sym-simpl (list '* x y)))
(defn sym+ [x y] (sym-simpl (list '+ x y)))
(defn sym- [x y] (sym-simpl (list '- x y)))



(defn sym-der [f v]
  (cond
    (= f v) 1
    (symbol? f) 0
    ;; from now on, f must be a list.
    (= (first f) '+) (sym+ (sym-der (nth f 1) v)
                           (sym-der (nth f 2) v))
    (= (first f) '*) (sym+ (sym* (nth f 2) (sym-der (nth f 1) v))
                           (sym* (nth f 1) (sym-der (nth f 2) v)))
    :else 0))

(sym-simpl 'x)
(sym-simpl 1)
(sym-der 'x 'x)
(sym-der 'x 'y)

;; R -> R^3
(defn c [t] (list t (* t t) (* t t t)))

;; R^3 -> R
;; (def-fn f [x y z] [])
(defn main [opts]
  (println "Hello, World!"))

