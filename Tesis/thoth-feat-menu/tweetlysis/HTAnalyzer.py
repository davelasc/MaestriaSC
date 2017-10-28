
from collections import Counter
import itertools

import numpy as np
import json
import TweetProcess as TP


def getUniqueHTList(HTList):
    return set(HTList)


def getAssociatedHashtags(tweets, original):
    HTList = []
    tweetsProcessed = 0

    for tweet in tweets:
        for hashtags in tweet['entities']['hashtags']:
            ht = hashtags[u'text'].lower()
            if ht != original.lower():
                HTList.append(ht)

    return HTList


def getMostUsed(HTList, number):
    counter = Counter(HTList)
    return counter.most_common(number)


def getHTtoHT(tweets):
    # for each tweet, take all HT
    # append all HTs to a list
    # make all the combinations
    # append the combinations to HTList
    # Create the counter

    HTList = []

    for tweet in tweets:
        tweetHT = []
        # Extract HTs on each tweet
        for hashtags in tweet['entities']['hashtags']:
            ht = hashtags[u'text'].lower()
            tweetHT.append(ht)

        # Create all te HT combinations
        for pair in itertools.combinations(tweetHT, 2):
            HTList.append(pair)

    counter = Counter(HTList)

    # for elem in counter:
    #     print str(elem) + " : " + str(counter[elem] )

    return counter
