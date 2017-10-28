 # Include nltk download here

"""A setuptools based setup module.
See:
https://packaging.python.org/en/latest/distributing.html
https://github.com/pypa/sampleproject
"""

# Always prefer setuptools over distutils
from setuptools import setup, find_packages
# To use a consistent encoding
from codecs import open
import os
import sys
import json
import tweetlysis.Auth as Auth
# from tweetlysis.gui.DirPicker import pickDir

def createDir(path):
    if not os.path.exists(path):
        try:
            os.makedirs(path)
        except OSError as e:
            print "Couldn't create config directory"
            print e
            sys.exit(1)

defaultDataDir = os.path.join(os.path.expanduser('~'), 'Documents', 'Thoth')
print 'Default data directory is {}.'.format(defaultDataDir)
useDefault = raw_input('Do you want to use this location? (y/n): ').lower()

dataDir = ''

while True:
    if useDefault == 'y' or useDefault == 'yes':
        dataDir = defaultDataDir
        createDir(dataDir)
        break
    elif useDefault == 'n' or useDefault == 'no':
        # dataDir = pickDir(defaultDataDir)
        break
    else:
        useDefault = raw_input('Please enter a valid input (y/n): ').lower()

dataDir = os.path.join(dataDir, 'data');
createDir(dataDir)

# Create config in hidden directory
configPath = os.path.join(os.path.expanduser('~'), '.thoth', 'config')
createDir(configPath)

with open(os.path.join(configPath, 'config'), 'w') as confFile:
    confFile.write(dataDir)

Auth.__createCredentials__(configPath)

sys.exit(1)
# Save data dir in config file


# print
# print
# print   "==========================================================="
# print """           Natural Language ToolKit Installation          """
# print   "==========================================================="
#
# try:
#     import nltk
#     nltk.download('all')
#     print "done"
# except Exception as e:
#     print e
#     raise ImportError('nltk was not installed')

#
# # Get the long description from the README file
# with open(path.join(here, 'README.md'), encoding='utf-8') as f:
#     long_description = f.read()
#
# setup(
#     name='tweet-lysis,
#
#     # Versions should comply with PEP440.  For a discussion on single-sourcing
#     # the version across setup.py and the project code, see
#     # https://packaging.python.org/en/latest/single_source_version.html
#     version='0.1',
#
#     description='A tweet analysis factory',
#     long_description=long_description,
#
#     # The project's main homepage.
#     url='https://github.com/a-rmz/tweet-lysis',
#
#     # Author details
#     author='Alejandro Ramirez',
#     author_email='armzprz@gmai.com',
#
#     # Choose your license
#     license='GPL-3.0',
#
#     # See https://pypi.python.org/pypi?%3Aaction=list_classifiers
#     classifiers=[
#         # How mature is this project? Common values are
#         #   3 - Alpha
#         #   4 - Beta
#         #   5 - Production/Stable
#         'Development Status :: 3 - Alpha',
#
#         # Indicate who your project is intended for
#         'Intended Audience :: Developers',
#         'Topic :: Software Development :: Network Analysis',
#
#         # Pick your license as you wish (should match "license" above)
#         'License :: GNU GPL-3.0',
#
#         # Specify the Python versions you support here. In particular, ensure
#         # that you indicate whether you support Python 2, Python 3 or both.
#         'Programming Language :: Python :: 2',
#         'Programming Language :: Python :: 2.6',
#         'Programming Language :: Python :: 2.7',
#         'Programming Language :: Python :: 3',
#         'Programming Language :: Python :: 3.3',
#         'Programming Language :: Python :: 3.4',
#         'Programming Language :: Python :: 3.5',
#     ],
#
#     # What does your project relate to?
#     keywords='twitter network analysis big data',
#
#     # You can just specify the packages manually here if your project is
#     # simple. Or you can use find_packages().
#     packages=find_packages(exclude=['contrib', 'docs', 'tests']),
#
#     # Alternatively, if you want to distribute just a my_module.py, uncomment
#     # this:
#     #   py_modules=["my_module"],
#
#     # List run-time dependencies here.  These will be installed by pip when
#     # your project is installed. For an analysis of "install_requires" vs pip's
#     # requirements files see:
#     # https://packaging.python.org/en/latest/requirements.html
#     install_requires=[
#         #### Requirements for Tweet-lysis ####
#
#         # Core API of the app
#         'tweepy',
#
#         # Plotting APIs
#         'numpy',
#         'matplotlib',
#
#         # Natural Language Processing
#         'nltk',
#
#     ],
#
# )
