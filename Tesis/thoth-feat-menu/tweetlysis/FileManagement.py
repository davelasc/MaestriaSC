# coding=utf-8

from tweepy import TweepError, RateLimitError
from time import gmtime, strftime, sleep
import datetime
import sqlite3 as db

import sys
import os
import json

import Auth

def saveTweetQuery(tweetQuery, queryPath, verbose=False):
    parsed = parseTweetQuery(tweetQuery, verbose)
    with open(queryPath, 'w') as query_file:
        json.dump(parsed, query_file)

    return parsed


def parseTweetQuery(tweetQuery, verbose=False):
    jsonQuery = []
    tweetsProcessed = 1

    try:
        for tweet in tweetQuery:
            try:
                jsonQuery.append(tweet._json)
                if verbose:
                    print '%d [%s] %s: %s' % (tweetsProcessed, tweet._json['created_at'], tweet._json['user']['screen_name'], tweet._json['text'][:60])
                tweetsProcessed += 1
            except StopIteration:
                break
    except Exception as e:
        return jsonQuery


    print '%d tweets processed' % (tweetsProcessed - 1)

    return jsonQuery


def loadTweetQuery(queryPath):
    path = os.path.join(queryPath, 'tweets.json')
    if os.path.exists(queryPath):
        with open(path, 'r') as query_file:
            tweetQuery = json.load(query_file)
            return tweetQuery
    else:
        return None


def saveWordcloudFile(path, name, buffer):
    try:
        genPath = os.path.join(path, 'generated', 'wordcloud', name + '_wordcloud.csv').encode('utf-8')
        with open(genPath, 'a') as csvFile:
            for tweet in buffer:
                fbuff = '%s,\n' % (tweet['text'].encode('utf-8'))
                csvFile.write(fbuff)

        return True
    except Exception as e:
        # Log here
        print e
        raise IOError('Error writing csv file')


def saveGraphCSV(path, name, buffer, graphType='ht2h2', type='undirected'):
    try:
        genPath = os.path.join(path, 'generated', 'graph', graphType).encode('utf-8')
        __saveNodes__(genPath, name, buffer)
        __saveEdges__(genPath, name, buffer, type)
    except Exception as e:
        raise IOError('Error writing csv file')


def __saveEdges__(path, name, buffer, type):
    try:
        with open(os.path.join(path, name + '_edges.csv'), 'a') as csvFile:
            fbuff = 'Source,Target,Weight,Type,\n'
            csvFile.write(fbuff)
            for pair in buffer:
                fbuff = '%s,%s,%d,%s,\n' %(pair[0].encode('utf-8'), pair[1].encode('utf-8'), buffer[pair], type)
                csvFile.write(fbuff)

        return True
    except Exception as e:
        print e
        raise IOError('Error writing edges csv')


def __saveNodes__(path, name, buffer):
    try:
        nodes = []
        for node in buffer:
            nodes.append(node[0].encode('utf-8'))
            nodes.append(node[1].encode('utf-8'))

        nodes = set(nodes)

        with open(os.path.join(path, name + '_nodes.csv'), 'a') as csvFile:
            fbuff = 'Id,Label,\n'
            csvFile.write(fbuff)
            for node in nodes:
                fbuff = '%s,%s,\n' %(node, node)
                csvFile.write(fbuff)

        return True
    except Exception as e:
        print e
        raise IOError('Error writing edges csv')

def addToIndex(indexPath, dir):
    currDir = dir.replace(path + '/', '').replace('_', ' ')
    date = currDir[0:15]
    term = currDir[16:]
    queryType = 'Hashtag' if (currDir[16] == '#') else 'User'

    # Right way to load and overwrite a JSON (DB maybe?)
    with open(indexPath, 'w') as indexFile:
        index = json.loads(indexFile)
        index['index'].append({
            'term': term,
            'type': queryType,
            'date': date,
            'relPath': dir
        })
        json.dump(index, indexFile)

def indexExisting(dataPath, dbPath):

    # return
    dirs = [os.path.join(dataPath,o) for o in os.listdir(dataPath) if os.path.isdir(os.path.join(dataPath,o))]

    indexArr = []
    for dir in dirs:
        currDir = dir.replace(dataPath + '/', '').replace('_', ' ')
        date = currDir[0:15]
        term = currDir[16:]
        queryType = 'Hashtag' if (currDir[16] == '#') else 'User'
        indexArr.append(
            (term, queryType, date, dir)
        )
        # indexArr.append({
        #     'term': term,
        #     'type': queryType,
        #     'date': date,
        #     'relPath': dir
        # })
    indexArr = tuple(indexArr)

    with db.connect(dbPath, detect_types=db.PARSE_DECLTYPES) as con:
        cur = con.cursor()
        cur.executemany(
            'INSERT INTO QueryIndex(Term, QueryType, QueryDate, Path) VALUES(?, ?, ?, ?);',
            indexArr
        )

        # cur.execute("""
        #     SELECT * FROM QueryIndex
        # """)
        #
        # rows = cur.fetchall()
        # for row in rows:
        #     print row


    # indexPath = os.path.join(path, 'index.json')
    # with open(indexPath, 'w') as indexFile:
    #     json.dump(indexArr, indexFile)


def listIndexed(dbPath):

    with db.connect(dbPath, detect_types=db.PARSE_DECLTYPES) as con:
        cur = con.cursor()
        cur.execute(
            'SELECT * FROM QueryIndex;'
        )

        rows = cur.fetchall()

        for row in rows:
            print row
