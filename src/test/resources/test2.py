#!/usr/bin/env python
# coding: utf-8

# In[1]:


#Python method with arguments
import sys
 
def getDataFromJava(arg1):
       
    arg1_val="Param 1: "+ arg1

    print(arg1_val)

    
    
arg1 = sys.argv[1]

getDataFromJava(arg1)


# In[ ]:




