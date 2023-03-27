(ns tic-tac-toe.util.getter
  (:require [clojure.string :as str]))

(declare get-input is-valid? inside-bounds?)

(defn get-valid-position
  [width height]
  (loop
    [input (get-input)]
    (if (is-valid? input width height)
      (map #(Integer/parseInt %) (str/split input #" "))
      (do
        (println "Invalid input. Try again.")
        (recur (get-input))))))

(defn- get-input
  []
  (println  "Select where you'll mark. Use \"row col\" to select where to mark.")
  (read-line))

(defn- is-valid?
  [input width height]
  (and
    (re-matches #"^\d+\s\d+$" input)
    (inside-bounds? input width height)))

(defn- inside-bounds?
  [input width height]
  (let [row (get (str/split input #" ") 0)
        col (get (str/split input #" ") 1)
        row (Integer/parseInt row)
        col (Integer/parseInt col)]
    (and (and (<= row height) (> row 0))
         (and (<= col width) (> col 0)))))