
from PyQt4 import QtGui
from . import App

class DirPicker(QtGui.QMainWindow):

    def __init__(self):
        super(DirPicker, self).__init__()

    def pickDir(self, baseDir):
        return QtGui.QFileDialog.getExistingDirectory(
            self,
            "Select directory",
            baseDir,
            QtGui.QFileDialog.ShowDirsOnly
        )

def pickDir(baseDir):
    app = App.getApp()
    dirPicker = DirPicker()
    return dirPicker.pickDir(baseDir)
