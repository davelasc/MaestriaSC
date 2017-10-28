import os
import sys
import json

from datetime import datetime
import Auth
import sqlite3 as db


class ConfigManager():

    def __init__(self):
        self.CONFIG_PATH = os.path.join(os.path.expanduser('~'), '.thoth', 'config')
        try:
            with open(os.path.join(self.CONFIG_PATH, 'config'), 'r') as f:
                self.DATA_PATH = f.readline().replace('\n', '')
                self.INDEX_PATH = self.getIndexPath()

            with db.connect(self.INDEX_PATH) as con:
                cur = con.cursor()
                cur.execute('CREATE TABLE IF NOT EXISTS QueryIndex(_id INTEGER PRIMARY KEY AUTOINCREMENT, Term TEXT, QueryType TEXT, QueryDate TEXT, Path TEXT);')

        except Exception as e:
            print "Config file not found. Please re-run the setup"
            print e
            sys.exit(1)

    def createDir(self, PATH):
        if not os.path.exists(PATH):
            try:
                os.makedirs(PATH)
            except OSError as e:
                print "Couldn't create the directory:"
                print PATH
                sys.exit(1)
        # Log here
        return PATH

    def getIndexPath(self):
        PATH = os.path.join(self.DATA_PATH, '.index.db')

        return PATH

    def newQueryPath(self, query_term):
        query_timestamp = datetime.now().strftime("%y%m%d_%H%M%S")
        base_name = query_timestamp + "_" + query_term.replace(" ", "_")
        base_path = os.path.join(self.DATA_PATH, base_name)

        self.createDir(os.path.join(base_path, 'generated', 'graph', 'ht2ht'))
        self.createDir(os.path.join(base_path, 'generated', 'graph', 'u2ht'))
        self.createDir(os.path.join(base_path, 'generated', 'graph', 'u2u'))
        self.createDir(os.path.join(base_path, 'generated', 'wordcloud'))

        query_path = os.path.join(base_path, "tweets.json")
        # Add to index
        # Log here
        return base_path
