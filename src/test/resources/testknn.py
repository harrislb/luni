import pandas as pd
import json
import requests
from sklearn.neighbors import NearestNeighbors
import numpy as np
import sys

def knn(key, cost):
   secret_key = key
   chosen_cost = cost
   k = 4

   #opts, args = getopt.getopt(argv,"s:c:")
   #print("opts ", opts)
   #print("args ", args)
   #for opt, arg in opts:
   #   if opt == '-s':
   #      secret_key = arg
   #   elif opt == '-c':
   #      chosen_cost = arg
   print ('Cost is ', chosen_cost)
   print ('Secret key is ', secret_key)
   print(str(type(chosen_cost)))

   chosen_cost = int(chosen_cost)

   df = create_df_with_api_data(secret_key, chosen_cost)
   indices = return_nearest_neighbors(df, k)
   arr = get_neighbor_school_names(chosen_cost, df, indices, k)
   print(arr)

#input is the exact cost of a chosen school they want similar options to
def create_df_with_api_data(secret_key, chosen_cost):
    #f = open('./luni-api-data.json')
    cost_lower = chosen_cost - 100
    cost_upper = chosen_cost + 100
    url = 'https://api.data.gov/ed/collegescorecard/v1/schools?api_key=' + secret_key + '&cost.tuition.in_state__range=' + str(cost_lower) + '..' + str(cost_upper)
    #data = urlopen(url)
    #data_json = json.loads(data)
    response = requests.get(url)
    data_json = response.json()

    cols = ["school name", "cost", "size", "location"]
    df = pd.DataFrame(columns=cols)

    for entry in data_json["results"]:
        #print(entry)
        #school = testdata["results"[i]["latest"]["school"]
        zipcode = entry['latest']['school']['zip']
        school = entry['latest']['school']['name']
        print("school: " + school)
        cost = entry['latest']['cost']['tuition']['in_state']
        size = entry['latest']['student']['size']
        location = zipcode
        df.loc[len(df.index)] = [school, cost, size, location]

        #pd.concat(df,zip)

    #df.loc[len(df.index)] = [chosen_school, chosen_cost, school_size, 0]
    df = df.fillna(0)
    return df

def return_nearest_neighbors(df, k):

    X = df.drop("school name", axis=1)
    X = X.drop("cost", axis=1)
    X = X.drop("location", axis=1)
    X_train = X.values

    y = df["cost"]
    y_train = y.values

    array = list(zip(X_train, y_train))

    nbrs = NearestNeighbors(n_neighbors=k, algorithm='ball_tree').fit(array)

    distances, indices = nbrs.kneighbors(array)
    return indices

def get_neighbor_school_names(cost, df, indices, k):
    idx = df.index[df['cost'] == cost]
    row = indices[idx]
    print(row)
    print(row[0][0])

    arr = []
    for i in range(k):
        arr.append(df['school name'][row[0][i]])

    return arr

arg1 = sys.argv[1]
arg2 = sys.argv[2]

knn(arg1,arg2)