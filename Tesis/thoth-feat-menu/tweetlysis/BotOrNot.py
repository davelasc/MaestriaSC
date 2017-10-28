
import botornot

import Auth

class BoN():
    """docstring for BON"""

    def __init__(self):
        twitterKey = Auth.getKeyIndex(0)
        self.bon = botornot.BotOrNot(**twitterKey)

    def checkSingle(self, user):
        return self.bon.check_account(user)

    def checkList(self, userList):
        result = []
        for user in userList:
            result.append(self.bon.check_account(user))
        return result
