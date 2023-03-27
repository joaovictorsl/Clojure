(ns tic-tac-toe.util.formatter
  (:require [clojure.string :as str]))

(declare get-lines-formatted format-line-as-string)

(defn format-grid
  [grid]
  (str/join "\n" (get-lines-formatted grid)))

(defn- get-lines-formatted
  [grid]
  (map #(format-line-as-string %) grid))

(defn- format-line-as-string
  [line]
  (loop [result ""
         idx 0]
    (if (= idx (dec (count line)))
      (str result (line idx))
      (recur (str result (line idx) " | ") (inc idx)))))

(format-grid [[1 2 3] [1 2 3] [1 2 3]])