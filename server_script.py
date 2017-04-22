
# # A very simple Flask Hello World app for you to get started with...

from flask import Flask ,request

import codecs


#######  vaibhav ###########

import urllib.request
import json
#import sys


#arg1 = sys.argv[1]
#arg2 = sys.argv[2]



app=Flask(__name__)

@app.route('/yash',methods=['GET','POST'])
def index():
    if request.method=="POST":
            # to create a JSON fro FEEd
            # issue, categoiry ,whom
        params=request.data
        mystr=" "
        if "Water pollution" in str(params):
            mystr=params+" #HELPMCD"
        else:
            mystr=params+" #Helpdelhigov"
        tweet(mystr)
        print(params)
        data = dict(item.split("=") for item in params.split(" $"))
       # full_feed = json.dumps(data)
        '''lst = []
        for i in yo:
            lst.append(yo)
        lst.append(full_feed)
        print(json.dumps(lst))'''
        yo.append(data)
        print(yo)
        return str(yo)
    else:
        return "bc"


@app.route('/mayank',methods=['GET','POST'])
def mayank():

    if request.method=="POST":

        param=request.data

        driver_data=[]

        d1={}
        d1['name']="Ravi"
        d1['location']="Sector62+Noida"
        d1['number']=9643023359
        driver_data.append(d1)

        d2={}
        d2['name']="Ram"
        d2['number']=9643023359
        d2['location']="Sector18+Noida"
        driver_data.append(d2)


        d3={}
        d3['name']="Priyesh"
        d3['number']=9643023359
        d3['location']="Agra"
        driver_data.append(d3)


        final_driver=driver_data[0]
        min_distance=100000000000.0000

        for i in range(0,len(driver_data)):
            new_driver=driver_data[i]

            url="https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+str(source)+"&destinations="+str(new_driver['location'])+"&key=AIzaSyDOf9GesBf554lyJKbwV8_BrBXNZY0g-Jg"
            response = urllib.request.urlopen(url)
            reader = codecs.getreader("utf-8")
            data = json.load(reader(response))
            distance=data["rows"][0]["elements"][0]["distance"]["text"]
            distance_miles=distance[:-3]
            distance_km=float(distance_miles)*(1.60934)

            new_driver['dist']=distance_km
            if float(new_driver['dist']) < min_distance:
                min_distance=float(new_driver['dist'])

                final_driver=new_driver


        final_str=str(final_driver['name'])+"#"+str(final_driver['location'])+"#"+str(final_driver['dist'])+"#"+str(final_driver['number'])

        return final_str







    else:


        driver_data=[]

        d1={}
        d1['name']="Ravi"
        d1['location']="Sector62+Noida"
        d1['number']=9643023359
        driver_data.append(d1)

        d2={}
        d2['name']="Ram"
        d2['number']=9643023359
        d2['location']="Sector18+Noida"
        driver_data.append(d2)


        d3={}
        d3['name']="Priyesh"
        d3['number']=9643023359
        d3['location']="Agra"
        driver_data.append(d3)


        final_driver=driver_data[0]
        min_distance=100000000000.0000

        for i in range(0,len(driver_data)):
            new_driver=driver_data[i]

            url="https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+"Sector18+Noida"+"&destinations="+str(new_driver['location'])+"&key=AIzaSyDOf9GesBf554lyJKbwV8_BrBXNZY0g-Jg"
            response = urllib.request.urlopen(url)
            reader = codecs.getreader("utf-8")
            data = json.load(reader(response))
            distance=data["rows"][0]["elements"][0]["distance"]["text"]
            distance_miles=distance[:-3]
            distance_km=float(distance_miles)*(1.60934)

            new_driver['dist']=distance_km
            if float(new_driver['dist']) < min_distance:
                min_distance=float(new_driver['dist'])

                final_driver=new_driver


        final_str=str(final_driver['name'])+"#"+str(final_driver['location'])+"#"+str(final_driver['dist'])+"#"+str(final_driver['number'])

        return final_str



def yash(params):

    data = dict(item.split("=") for item in params.split("+"))

    full_feed = json.dumps(data)
    tweet(data)
    '''lst = []
    for i in yo:



        lst.append(yo)
    lst.append(full_feed)
    print(json.dumps(lst))'''
    yo.append(full_feed)
    print(yo)
    return str(yo)





# http://dev.twitter.com/apps/myappid
CONSUMER_KEY = 'uczGAH9GstoVRKbBVqtT8kFl4'
CONSUMER_SECRET = 'qtXtMXSndfhYNUKi6Vgn7qopNW8r75mQuq2yp4Z4FXihQksBW5'
# http://dev.twitter.com/apps/myappid/my_token
ACCESS_TOKEN_KEY= '3980854518-2lhVDzwwOq2CA4QZXHB1Bhx5mhthcTMYKmps9Pj'
ACCESS_TOKEN_SECRET= 'fpF5m0EO1GTjHdOwdqJOBrh6E3TeA2M6fH44T9WAESLmW'

def tweet(status):
    '''
    updates the status of my twitter account
    requires tweepy (https://github.com/joshthecoder/tweepy)
    '''
    if len(status) > 140:
        raise Exception('status message is too long!')
    auth = tweepy.OAuthHandler(CONSUMER_KEY, CONSUMER_SECRET)
    auth.set_access_token(ACCESS_TOKEN_KEY, ACCESS_TOKEN_SECRET)
    api = tweepy.API(auth)
    result = api.update_status(status)
    return result



if __name__=="__main__":
    app.run()

