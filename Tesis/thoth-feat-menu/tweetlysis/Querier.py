
import tweepy
import os

import Auth
import FileManagement as FM
from config.ConfigManager import ConfigManager

class Querier:
    def __init__(self):
        self.configManager = ConfigManager()
        self.API = Auth.getAPI(self.configManager.CONFIG_PATH)

    def queryByHashtag(self, HT, since, until, resultType="recent", items=100, lang="es", savedEnabled=True):
        query_term = "#" + HT
        tweets = tweepy.Cursor(
            self.API.search,
            q=query_term,
            result_type=resultType,
            since=since,
            until=until,
            lang=lang,
            show_user=True
        ).items(items)

        query_dir = None
        if savedEnabled:
            query_dir = self.configManager.newQueryPath(query_term)
            query = FM.saveTweetQuery(tweets, os.path.join(query_dir, 'tweets.json'), verbose=True)
        else:
            query = FM.parseTweetQuery(tweets)

        return [query, query_dir]

    def queryByUser(self, username, since, until, resultType="recent", items=100, savedEnabled=True):
        tweets = tweepy.Cursor(
            self.API.user_timeline,
            id=username,
            result_type=resultType,
            since=since,
            until=until
        ).items(items)

        if savedEnabled:
            query_dir = self.configManager.newQueryPath(username)
            query = FM.saveTweetQuery(tweets, os.path.join(query_dir, 'tweets.json'), verbose=True)
        else:
            query = FM.parseTweetQuery(tweets)

        return [query, query_dir]

    def loadSavedQuery(self, term, time):
        path = os.path.join(self.configManager.DATA_PATH, (time + " " + term).replace(" ", "_"))
        return [FM.loadTweetQuery(path), path]

    def getGeneratedQueryPath(self, term, time):
        return os.path.join(self.configManager.DATA_PATH, (time + " " + term).replace(" ", "_"), 'generated')

    def getRateLimits():
        json.dumps(self.API.rate_limit_status(), sort_keys=True, indent=2, separators=(',', ': '))
