# Tweet-lysis
An analysis tool for twitter.

## This is the developer README

## Installation
* Create a Twitter app
    (Taken from [here](https://themepacific.com/how-to-generate-api-key-consumer-token-access-key-for-twitter-oauth/994/))
   1. Go to https://dev.twitter.com/apps/new and log in, if necessary
   2. Enter your Application Name, Description and your website address. You can leave the callback URL empty.
   3. Accept the TOS, and solve the CAPTCHA.
   4. Submit the form by clicking the **Create your Twitter Application**
   5. Keep this open, you'll need it later


* Clone this repo
    * over HTTP
        * `git clone https://github.com/a-rmz/tweet-lysis.git`
    * over SSH
        * `git clone git@github.com:a-rmz/tweet-lysis.git`


* Switch to the development branch
    * `git checkout develop`


* Install the required packages
    * `pip install -r requirements.txt`
    * Depending on your OS, `pip` it might be required to run as root.


* Run the setup
    * `python setup.py install`
    * This will create the corresponding directories
    * Go grab a beer while [`nltk`](http://www.nltk.org/) downloads everything it requires. (This might -and will- take a while)
    * When the install is done, you will be notified with a warm `done` message.
