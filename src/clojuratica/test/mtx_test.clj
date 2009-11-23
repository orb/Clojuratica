(use 'clojure.contrib.duck-streams)

(defn read-table [filename]
  "Reads a file with whitespace-delimited, tabular data."
  (map #(map (fn [#^String s] (if-not (= s "") (read-string s)))
         (. (. #^String % trim) split "\\s+"))
       (read-lines filename)))

(defn sparse-eigensystem [m n indices vals]
  "Given 3 vectors and the matrix dimensions, returns the eigenvalues and eigenvectors using mathematica."
  (math :seq-fn :N (Eigensystem (SparseArray (-> ~indices (N ~vals)) [~m ~n]))))
  ;(math ["vals" vals
  ;       "indices" indices
  ;       "m" m
  ;       "n" n]
  ;  "Eigensystem[SparseArray[indices->N[vals],{m,n}]]"))

;(def m (read-table "C:\\Users\\Garth\\Downloads\\matrix"))
;(dorun m)

;(def rows (map first m))
;(def cols (map second m))
;(def values (doall (map #(nth % 2) m)))
;(def indices (doall (map list rows cols)))

;(time (def es (sparse-eigensystem indices values 2856 2856)))
(let [m (read-table "C:\\Users\\Garth\\Downloads\\matrix")]
	(time (def es (sparse-eigensystem 2856 2856
																		(map #(list (first %) (second %)) m)
																		(map #(nth % 2) m)))))

