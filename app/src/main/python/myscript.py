import datetime as dt
import pandas as pd
import cv2
from pandas_datareader import data as wb
import matplotlib.pyplot as plt
import numpy as np
import io
import base64
from PIL import Image

def main(ticker):
    startDate = dt.datetime(2010, 1, 1)
    company_data = pd.DataFrame()
    company_data = wb.DataReader(ticker, data_source='yahoo', start=startDate)['Adj Close']

    fig = company_data.plot(figsize=(15,10)).get_figure()
    fig.canvas.draw()

    img = np.fromstring(fig.canvas.tostring_rgb(),dtype=np.uint8,sep='')
    img = img.reshape(fig.canvas.get_width_height()[::-1]+(3,))
    img = cv2.cvtColor(img,cv2.COLOR_RGB2BGR)

    pil_im = Image.fromarray(img)
    buff = io.BytesIO()
    pil_im.save(buff,format="PNG")
    img_str = base64.b64encode(buff.getvalue())

    return ""+str(img_str, 'UTF-8')