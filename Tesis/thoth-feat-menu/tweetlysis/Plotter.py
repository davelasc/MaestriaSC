from collections import Counter
import numpy as np
import matplotlib.pyplot as plt

import plotly
from plotly.tools import FigureFactory as FF
import plotly.graph_objs as go


def plotBarsAndTableCount(HTList, plottype, plotTitle="Plot"):
    index, counts = zip(*HTList)
    index = list(index)
    counts = list(counts)

    data = [[plottype, 'count']] + HTList
    figure = FF.create_table(data, height_constant=60)

    bars = go.Bar(x=index, y=counts, xaxis='x2', yaxis='y2', marker=dict(color='#48C20C'), name=plottype)
    figure['data'].extend(go.Data([bars]))

    figure.layout.yaxis.update({'domain': [0, .4]})
    figure.layout.yaxis2.update({'domain': [.55, 1]})
    figure.layout.xaxis.update({'domain': [.33, .66]})

    figure.layout.yaxis2.update({'anchor': 'x2'})
    figure.layout.xaxis2.update({'anchor': 'y2'})

    figure.layout.margin.update({'t':75, 'l':50})
    figure.layout.update({'title': plotTitle})
    figure.layout.update({'height':800})

    # Make text size larger
    for i in range(len(figure.layout.annotations)):
        figure.layout.annotations[i].font.size = 20

    return plotly.offline.plot(
        figure,
        filename=plotTitle.lower().replace(" ", "_")
    )

def plotBars(HTList, plottype, plotTitle="Plot"):
    index, counts = zip(*HTList)
    index = list(index)
    counts = list(counts)

    return plotly.offline.plot({
        "data": [go.Bar(x=index, y=counts, marker=dict(color='#48C20C'), name=plottype)],
        "layout": go.Layout(title=plotTitle)
    }, filename=plotTitle.lower().replace(" ", "_"))


def plotTimeSeries(HTList, plotTitle="Time series"):
    index, counts = zip(*HTList)
    index = list(index)
    counts = list(counts)
    # print counts

    return plotly.offline.plot({
        "data": [go.Scatter(x=index, y=counts)],
        "layout": go.Layout(title=plotTitle)
    },
    filename=plotTitle.lower().replace(" ", "_")
    )

# def plotTable(headerList, dataList):
#     matrix = headerList + dataList
#     table = FF.create_table(matrix)
#     return plotly.offline.plot(table)

# def plotTupleCount(HTList):
#     index, counts = zip(*HTList)
#
#     indexes = np.arange(len(index))
#     width = 0.7
#     plt.bar(indexes, counts, width)
#     plt.xticks(indexes + width * 0.5, index)
#     plt.show()
