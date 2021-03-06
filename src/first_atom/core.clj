

(ns first-atom.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clj-memory-meter.core :as mm]
            [clojure.string :as s]))

(def dictionary "english.txt")
(def words (atom []))

;(mm/measure(time(def trie1 (reduce add-to-trie {} (take 20 @words)))))

(defn tokenize []
  (let [english
         (s/split
           (slurp (io/resource dictionary)) #"\r\n")]
    (swap! words into english)))

(defn add-to-trie [trie x]
    (update-in trie 
               x
               (fn [prev new] 
                 (merge-with #(if (number? %1) 
                                (+ %1 %2) %2) prev new))
               {:f 1 :& true}))



 (defn get-str-prefix
  [trie1 prefix]
  (loop [prefixes [prefix]
         result {}]
    (if (empty? prefixes)
      (keep key (sort-by val > result))
      (recur (flatten (map (fn [prfx] 
                             (map #(str prfx %)
                                  (filter (fn [k] (not (= k :f)))
                                          (filter (fn [k] (not (= k :&)))
                                                  (keys (get-in trie1 prfx))))))
                           prefixes))
             (reduce
              (fn [m prfx] (assoc m prfx (:f (get-in trie1 prfx) 1))) 
                     result (keep #(if (:& (get-in trie1 %)) %)
                                  prefixes))))))
