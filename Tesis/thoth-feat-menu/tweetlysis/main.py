import time

import json

import logging

import sys



from Querier import Querier

#from BotOrNot import BoN



import TweetProcess as TP

import HTAnalyzer as HTA

import Plotter

import UserAnalyzer as UA



from config.ConfigManager import ConfigManager as CM

import FileManagement as FM



def main():

        # cm = CM()

        # FM.indexExisting(cm.DATA_PATH, cm.INDEX_PATH)

        # FM.listIndexed(cm.INDEX_PATH)

        #



        # bon = BoN()

        #



        # tweets, path0 = querier.queryByHashtag(

        #     term,

        #     since="2017-01-07",

        #     until="2017-01-09",

        #     resultType="recent",

        #     items=10,

        #     savedEnabled=True

        # )

        # print tweets

        # tweets = querier.queryByUser("hlodynjar", since="2016-11-07", until="2015-11-09", resultType="recent", items=10, savedEnabled=False)

        # term = "hlodynjar"

        # time0 = "161110_10.45.15"

        # time1 = "161108 00:00:01"

        # tweets, path0 = querier.loadSavedQuery(term, time0)

        # tweets1, path1 = querier.loadSavedQuery(term, time1)

        #

        # tweets = tweets0 + tweets1

        #



        #para buscar los queries se tiene que generar una carpeta con el nombre del queryPath

        #Con un 0_ antes. En este caso sería 0_prueba. El json tiene que llamarse tweets.json

        #Se deben generar las carpetas dentro de la carpeta Thoth:

        #0_sanvalentin -> [tweets.json (archivo)] [generated]->[graph]->[ht2ht],[u2ht],[u2u],[wordcloud]

        querier = Querier()

        term = "prueba"

        tweets, path0 = querier.loadSavedQuery(term, "0")



        ht2ht = HTA.getHTtoHT(tweets)

        u2ht = UA.getUserToHT(tweets)

        u2u = UA.getUserToUser(tweets)





        FM.saveGraphCSV(path=path0, name=term, graphType="u2ht", buffer=u2ht, type="directed")

        FM.saveGraphCSV(path=path0, name=term, graphType="ht2ht", buffer=ht2ht, type="undirected")

        FM.saveGraphCSV(path=path0, name=term, graphType="u2u", buffer=u2u, type="directed")

        FM.saveWordcloudFile(path=path0, name=term, buffer=tweets)





        # term = raw_input('Ingresa el HT a buscar, sin el \"#\"\n')

        # since = raw_input('A partir de que fecha? (aaaa-mm-dd)\n')

        # until = raw_input('Hasta que fecha? (aaaa-mm-dd)\n')

        # items = raw_input('Numero de tweets a procesar:\n')

        # tweets, queryPath = querierObj.queryByHashtag(term, since, until, resultType="recent", items=int(items), savedEnabled=True)

        #

        # ht2ht = HTA.getHTtoHT(tweets)

        # u2ht = UA.getUserToHT(tweets)

        # u2u = UA.getUserToUser(tweets)

        #

        # FM.saveGraphCSV(path=queryPath, name=term, graphType="u2ht", buffer=u2ht, type="directed")

        # FM.saveGraphCSV(path=queryPath, name=term, graphType="ht2ht", buffer=ht2ht, type="undirected")

        # FM.saveGraphCSV(path=queryPath, name=term, graphType="u2u", buffer=u2u, type="directed")

        # FM.saveWordcloudFile(path=queryPath, name=term, buffer=tweets)



        # TP.printTweets(tweets)

        # for tweet in tweets:

        #     print json.dumps(tweet, sort_keys=True,indent=2, separators=(',', ': '))

        #     print "-----------"



        # HTList = HTA.getAssociatedHashtags(tweets, term)

        # Plotter.plotBars(HTA.getMostUsed(HTList, 15), 'HTs', 'Used Hashtags')

        # Plotter.plotTimeSeries(TP.getTimeSeries(tweets), "Times")



        # users = UA.getMostActiveUsers(tweetsAsJSON, 10)

        # Plotter.plotTupleCount(users, "Most active users", "users")



        # TP.printTweets(tweetsAsJSON)

        # HTList = HTA.getAssociatedHashtags(tweetsAsJSON, HT)

        # TP.printList(HTList)

        # WordOccurrences = TP.countWordOccurrences(tweetsAsJSON, HTList)







if __name__ == "__main__":

    main()


