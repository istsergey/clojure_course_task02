(ns clojure-course-task02.core
  (:gen-class))


(defn getName[file]  
        (.getName file)
        )

(defn getFileName[files fileName]
                 (pmap getName (filter #(.isFile %) (filter #(re-find (re-pattern fileName)
                         (getName %))  files)))
                 )

(defn find-files [file-name path]
  (flatten (pmap  #(getFileName % file-name) (partition-all 100 (file-seq (clojure.java.io/file path)))))
  )

; Еще один варинт решения
(defn find-filesVer2 [file-name path]
  (pmap getName (filter #(.isFile %) (filter #(re-find (re-pattern file-name)
          (getName %)) (file-seq (clojure.java.io/file path)))))
  )

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path] 
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path))))))
