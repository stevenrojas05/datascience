#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Jan 16 16:06:32 2020

@author: henry
"""

from flask import Flask, jsonify
from flask_cors import CORS 

import turicreate as tc

app = Flask(__name__)
CORS(app)

#Cargo modelo
model = tc.load_model("my_model.model")

#Función para generar un json con la recomendación
def create_output(model_recommendation, users_to_recommend):    
    
    recomendation = model_recommendation.recommend(users_to_recommend)
    
    df_rec = tc.SFrame.to_dataframe(recomendation)

    print(df_rec)
    
    df_rec = df_rec.drop(['score','rank'],axis=1) 
    
    df_rec['recommendedProducts'] = df_rec['item_id'].groupby(df_rec['user_id']).transform(lambda x: ','.join(x.astype(str))).drop_duplicates()
          
    df_output = df_rec[['user_id', 'recommendedProducts']].drop_duplicates().set_index('user_id').dropna()

    print(df_output)                         
    
    return df_output

@app.route('/users')
def recommend_new_user():
        
    df_recomendation = create_output(model, ['-1']).iloc[0].to_dict()

    return jsonify(df_recomendation)

@app.route('/user/<int:user_to_rc>')
def recommend_user(user_to_rc):
        
    user=str(user_to_rc)   
    
    df_recomendation = create_output(model, [user]).iloc[0].to_dict()

    return jsonify(df_recomendation)






if __name__ == '__main__':
    app.debug = True
    app.run()
