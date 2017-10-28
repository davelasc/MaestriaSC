
from collections import Counter

import numpy as np
import json
import TweetProcess as TP


def getMostActiveUsers(tweets, count=5):
    users = []
    for user in tweets:
        u = user['user']
        userTuple = (u['id'], u['screen_name'])
        users.append(userTuple)

    counter = Counter(users)
    return counter.most_common(count)


def getUserToHT(tweets):
    # For each tweet:
    #   Get the user screen_name
    #   For each HT:
    #       Make a tuple (user, HT)
    #       Append to a list
    # Pass the list to a counter
    # Return the counter
    userList = []

    for tweet in tweets:
        user = tweet['user']['screen_name']
        # Extract HTs on each tweet
        for hashtags in tweet['entities']['hashtags']:
            ht = hashtags[u'text'].lower()
            userList.append((user, ht))

    return Counter(userList)

def getUserToUser(tweets):
    users = []

    for tweet in tweets:
        source = tweet['user']['screen_name']
        # Take the users
        for user in tweet['entities']['user_mentions']:
            users.append((source, user['screen_name']))

    return Counter(users)

def getTargetUsers(tweets):
    targets = []
    for status in tweets:
        try:
            targets.append(status['retweeted_status']['user']['screen_name'])
        except Exception as e:
            pass

    counter = Counter(targets)
    print counter.most_common(20)
