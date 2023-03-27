(ns tic-tac-toe.core
  (:require [tic-tac-toe.util.formatter :as formatter]
            [tic-tac-toe.util.game :as game]))

(defn -main
  []
  (loop [grid [[" " " " " "] [" " " " " "] [" " " " " "]]
         player "X"]
    (println (formatter/format-grid grid))
    (println (str "It's your turn, player " player "."))
    (recur
      (game/make-move player grid)
      (game/change-player player))))

(println (-main))
