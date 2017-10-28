import tweepy
import sys
import os

import json

# Load the twitter keys
def getKey(keyfile):
	try:
		with open(keyfile) as fin:
			key = json.load(fin)
	except FileNotFoundError as e:
		print "Exception found"
		sys.exit(1)
	# Log here
	return key

def getAPI(confPath):
	key_path = os.path.join(confPath, "meta")
	keys = getKey(key_path)

	consumer_key = keys['keys'][0]['consumer_key']
	consumer_secret = keys['keys'][0]['consumer_secret']
	access_token = keys['keys'][0]['access_token']
	access_secret = keys['keys'][0]['access_secret']

	auth = tweepy.AppAuthHandler(consumer_key, consumer_secret)
	# auth.set_access_token(access_token, access_secret)

	api = tweepy.API(auth, wait_on_rate_limit=True,
				   wait_on_rate_limit_notify=True)

	if not api:
		print ("Can't Authenticate")
		sys.exit(-1)

	return api

def getKeyIndex(index):
	key_path = os.path.join(os.path.abspath(os.path.dirname(__file__)), "config", "meta.json")
	keys = getKey(key_path)

	consumer_key = keys['keys'][index]['consumer_key']
	consumer_secret = keys['keys'][index]['consumer_secret']
	access_token = keys['keys'][index]['access_token']
	access_secret = keys['keys'][index]['access_secret']

	return {
		"consumer_key": consumer_key,
		"consumer_secret": consumer_secret,
		"access_token": access_token,
		"access_token_secret": access_secret
	}


def __validateUser__(consumer_key, consumer_secret, access_token, access_secret):
	try:
		auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
		auth.set_access_token(access_token, access_secret)
		API = tweepy.API(auth)
		me = API.me()
		print
		print "Authenticated successfully as " + me.screen_name
	except tweepy.TweepError as e:
		print e
		return None

	return True

def __createCredentials__(path):
    path = os.path.join(path, 'meta')
    cons_key = raw_input('Enter the consumer key: ')
    cons_sec = raw_input('Enter the consumer secret: ')
    acc_tok = raw_input('Enter the access token: ')
    acc_sec = raw_input('Enter the access secret: ')

    # As long as credentials are not valid, ask for them
    while not __validateUser__(cons_key, cons_sec, acc_tok, acc_sec):
        print ""
        print "Error: The credentials provided are not valid"
        cons_key = raw_input('Enter the consumer key: ')
        cons_sec = raw_input('Enter the consumer secret: ')
        acc_tok = raw_input('Enter the access token: ')
        acc_sec = raw_input('Enter the access secret: ')
        print ""

    with open(path, 'w') as f:
        credentials = {"keys": [{
            "consumer_key": str(cons_key),
            "consumer_secret": str(cons_sec),
            "access_token": str(acc_tok),
            "access_secret": str(acc_sec)
        }]}
        json.dump(credentials, f)
