#!/usr/bin/env python
# coding: utf-8

# In[1]:


#Python method with arguments
import sys
 
def getDataFromJava(arg1,arg2):
       
    arg1_val="Param 1: "+ arg1
    arg2_val="Param 2: " + arg2
   
    print(arg1_val)
    print(arg2_val)
        
    
    
arg1 = sys.argv[1]
arg2 = sys.argv[2]

getDataFromJava(arg1,arg2)


# In[ ]:




