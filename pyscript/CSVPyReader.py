#coding:utf-8
import sys
paths = ['',
 '/usr/local/lib/python2.7/dist-packages'
 '/usr/lib/python2.7',
 '/usr/lib/python2.7/plat-x86_64-linux-gnu',
 '/usr/lib/python2.7/lib-tk',
 '/usr/lib/python2.7/lib-old',
 '/usr/lib/python2.7/lib-dynload',
 '/usr/local/lib/python2.7/dist-packages',
 '/usr/lib/python2.7/dist-packages',
 '/usr/lib/python2.7/dist-packages/PILcompat',
 '/usr/lib/python2.7/dist-packages/gst-0.10',
 '/usr/lib/python2.7/dist-packages/gtk-2.0',
 '/usr/lib/python2.7/dist-packages/pandas',
 '/usr/lib/pymodules/python2.7',
 '/usr/lib/python2.7/dist-packages/IPython/extensions']
paths = sys.path
"""
count = 0
for path in paths:
	sys.path.insert(count, path)
	count = count + 1

from pandas import DataFrame,read_csv
data = read_csv('/home/alex/Documents/ProjectDataSources/股票历史数据ALL.csv', sep='\t')

"""
