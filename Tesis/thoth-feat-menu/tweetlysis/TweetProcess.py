from tweepy import TweepError
from collections import Counter
from datetime import datetime
import pytz

import HTAnalyzer as HTA
import WordPreprocess as WP

import json


def printTweet(status, index):
    print "%d (%d) [%s] %s: %s" % (index, status['id'], status['created_at'], status['user']['screen_name'], status['text'][:60])
    # print "%s: %s" % (status['user']['screen_name'], status['text'][:40])

def printTweets(tweets):
    i = 1
    for status in tweets:
        printTweet(status, i)
        i += 1

def printList(HTList):
    for ht in HTList:
        print ht

def countWordOccurrences(tweets, HTList, most_used=10):
    HTUniqueList = HTA.getUniqueHTList(HTList)
    wordsWithStop = []
    for tweet in tweets:
        wordsWithStop += WP.preprocess(tweet['text'], lowercase=True)

    words = [term for term in wordsWithStop
            if term not in WP.stop and
            not term.startswith(('#', '@'))]
    counter = Counter(words)

    mostUsed = counter.most_common(most_used)
    print "The most used words were:"
    for word in mostUsed:
        print "\t %s with %d occurrences" % (word[0], word[1])

    return mostUsed

def getTimeSeries(tweets):
    times = []
    for tweet in tweets:
        time = datetime.strptime(tweet['created_at'],"%a %b %d %H:%M:%S +0000 %Y").replace(second=0, microsecond=0, tzinfo=pytz.UTC)
        times.append(time)

    c = Counter(times)
    times = []
    for entry in c:
        tup = (entry, c[entry])
        times.append(tup)

    times.sort()
    return times
