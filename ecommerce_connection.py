#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Dec 11 06:59:07 2019

@author: henry
"""


from sqlalchemy import create_engine
from sqlalchemy import text
from pandas import DataFrame

class EcommercePostgres:

    def getConnection(self):
        # create connection        
        engine = create_engine('postgres://postgres:root@localhost:5432/ecommerce')
        return engine
    
    def getDataframe(self):

        sql = text('select item_id, user_id, rating from reviews')
        engine = self.getConnection()
        result = engine.execute(sql)
        ratings = DataFrame(result.fetchall())
        ratings.columns = result.keys()

        return ratings


