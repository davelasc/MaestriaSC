import json
import tweepy
from tweepy import Stream
from tweepy.streaming import StreamListener

from config.ConfigManager import ConfigManager
import Auth

class Streamer(StreamListener):

    def __init__(self):
        self.count = 0

    def on_data(self, data):
        try:
            tweet =  json.loads(data)
            print '%d <%s> [%s] %s: %s' % (
                self.count,
                tweet['id'],
                tweet['created_at'],
                tweet['user']['screen_name'],
                tweet['text'][:90]
            )
            self.count += 1
            with open('wikileaks.json', 'a') as f:
                f.write(data)
                return True
        except BaseException as e:
            print('Error on_data: %s' % str(e))
        return True

    def on_error(self, status):
        print(status)
        return True

auth = tweepy.OAuthHandler('6Njc8zInNLlEmEcLPKRLFr6Ut', 'sADvVvGa3U6ZTrbOlmyeTqGGxKkmozUQXBTFsYROMxYSs6lQAz')

auth.set_access_token('2346474254-NotkoZ6pDX3qmmBWaRSTmB5ZAbAtfuQFkQP7Jyj', 'RKl6nfWdOSUPcIoTWFseh6ExdHrlY1mbN9A2Wn9K3ks0Z')

api = tweepy.API(auth)

streamer = Streamer()
twitter_stream = Stream(auth, streamer)
twitter_stream.filter(track=['#GobiernoEspia'])
