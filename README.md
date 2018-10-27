# Team Name
**HackOverflow**

# Members
Ayush Jain,Deepak Jain

# College
BITS Pilani

# City
Pilani

# Theme
Find better ways to save lives and prevent economic losses through mechanisms to predict, prevent, or manage the impact of natural disasters.

# Challenge
Earthquake is one of the most devastating natural calamities that takes thousands of lives and leaves millions more homeless and deprives them of the basic necessities. A survey from United Stated Geological Survey group shows that the last decade experienced approximately 450,000 deaths due to earthquakes. It breaks the backbone of the economy of a nation. This threat cannot be averted by mankind, but if predicted, the damage can be minimized. So, we need a tool to accurately predict earthquakes and to make unaware people aware of the basic steps and precautions that need to be taken during earthquakes.

# Solution
**Natural Time** (“NT”) refers to the concept of using small earthquake counts, for example of M>3 events, to mark the intervals between large earthquakes, for example M>6 events. It is particularly useful in describing complex stochastic nonlinear systems characterized by fat-tail statistics rather than Gaussian normal statistics.
We introduce the idea of **nowcasting**, a term originating from economics and finance. The goal of nowcasting is to determine the current state of the fault system, or put another way, the current state of progress through the earthquake cycle. 
Nowcasting enables us to forecast higher magnitude earthquakes by taking into account the number of smaller tremors in the same region. The technique used for this purpose are **Long Short Term Memory(LSTM) Neural Networks**. They are special kind of networks which have ability to use the previous input to predict the next value. These networks are perfectly suited for tasks involving time series analysis. Since our data, used for now casting is similar to time series, we decided to use this network.

  Actually using nowcasting we could form a Commulative Distribution Function(CDF) of the nowcast.
                         CDF =  P(n < n(t))
                            where n(t) denote the no. of small earthquakes since the last large earthquake.
  Concretely speaking we could calculate **Earthquake Potential Score(EPS)** using the predicted nowcast and the CDF. The EPS score will denote the probability that a substantiallly big earthquake(5 and above) can take place in near future in your area. Thus a high value of EPS, say 90%, could serve as a alarm for the people in that region and might help them to prepare beforehand for the calamity.
  
  We faced a few challenges in the implementation part:
  
  1. The earthquake data is available only for last 100 years or so. And since deeplearning requires enormous data, it became an obvious problem.
  
  2. Even bigger problem was the **Completeness value** for the data available is very poor. The small earthquakes have started to been reported only recently. Because of that we had to ignore a large chunk of data as it would have led the neural network to learn a wrong trend. Even as recent as 1990, Mc(completeness value) is 4, which means that only earthquakes above 4 are recorded completely. But with advancement in technology, it would definately improve and then the method would become stronger. 


**Features**
1. **One Click Probability of earthquake using Realtime Location**
Using the **Map API** and our **machine learning alogorithm** we will get a realtime location of the user. After this when User click "Predict" button of the app, using the data, our algorithm will predict the probability of earthquake at that region in upcoming time. In case **internet facility is not present** at the location our app will automatically **send the location and time to our server** and server will **text the probability of earthquake** to the user. This will help us to tackle the slow/No Internet problem.

2. **Nearby hospitals and emergency contacts**
Our app will have a section that will show the **hospital nearby** using the present location of the user and their contacts and one click call **emergency contacts** which user can use in case of emergency

3. **Gamification of general guidelines**
Most of the death in earthquakes occurs due to the **lack of awareness** among people. To make people aware of the guidelines and safety measures during earthquake we will make a **fun game** through which user can learn them in a fun way. 

4. **Eathquake Alert**
We will send real time notification update through **Azure push messaging** Service to all the nearby areas when earthquake has striken. We can also send **text message** with the help of government to all the people nearby.
We will build an **arduino** device which would take your location and store it in **Azure IOT Hub** to tackle low network connectivity regions.

# Technology Used
Machine learning,
Game Development,
App Development,
Azure Cloud Services,
Azure ML Lab,
Azure Machine Learning Service
Azure Batch AI Compute
Azure IOT Hub
Arduino

# Dataset
We have collected all the earthquakes in the indo-eurasia tectonic plate from USGS website for the years 1900 till date.


# References
To develop this idea we have taken references from many research papers(present in Research papers folder) but most relevant ones were:

Rundle, J. B., D. L. Turcotte, A. Donnellan, L. Grant Ludwig, M. Luginbuhl, and G. Gong (2016), Nowcasting earthquakes, Earth and Space Science, 3, 480–486, doi:10.1002/2016EA000185.

Wang Q., Guo Y., Yu L. , Pan L., (2017) Earthquake Prediction based on Spatio-Temporal Data Mining: An LSTM Network Approach, Transactions on Emerging Topics in Computing, 2168-6750 

# Acknowledgements
We wouldn't have been able to complete this project without the insights of our professor **Dr. Sumanta pasari** who had been an active researcher in seismeity and earthquakes around the Himalayan Region.















