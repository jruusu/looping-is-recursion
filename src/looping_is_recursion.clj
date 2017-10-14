(ns looping-is-recursion)

(defn power [base exp]
  (let [helper (fn [acc base exp]
                 (cond
                   (zero? exp) 1
                   (= exp 1) acc
                   :else (recur (* acc base) base (dec exp))))]
    (helper base base exp)))

(defn last-element [a-seq]
  (if (<= (count a-seq) 1)
    (first a-seq)
    (recur (rest a-seq))))

(defn seq= [seq1 seq2]
  (cond
    (and (empty? seq1) (empty? seq2)) true
    (not (and (seq seq1) (seq seq2))) false
    (not= (first seq1) (first seq2)) false
    :else (recur (rest seq1) (rest seq2))))

(defn find-first-index [pred a-seq]
  (loop [s a-seq
         i 0]
    (cond
      (empty? s) nil
      (pred (first s)) i
      :else (recur (rest s) (inc i)))))

(defn avg [a-seq]
  (loop [s a-seq
         acc 0
         n 0]
    (if (empty? s)
      (/ acc n)
      (recur (rest s) (+ acc (first s)) (inc n)))))

(defn toggle [a-set elem]
  (if (contains? a-set elem) (disj a-set elem) (conj a-set elem)))

(defn parity [a-seq]
  (loop [s a-seq
         acc #{}]
    (if (empty? s)
      acc
      (recur (rest s) (toggle acc (first s))))))

(defn fast-fibo [n]
  (loop [acc0 0
         acc1 1
         n n]
    (if (zero? n)
      acc0
      (recur acc1 (+ acc0 acc1) (dec n)))))

(defn cut-at-repetition [a-seq]
  (loop [a-seq a-seq
         acc []
         seen #{}]
    (let [x (first a-seq)]
      (if (or (empty? a-seq) (contains? seen x))
        acc
        (recur (rest a-seq) (conj acc x) (conj seen x))))))

