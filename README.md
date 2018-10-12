# Team Name
**HackOverflow**

# Members
Ayush Jain,Deepak Jain

# College
BITS Pilani

# City
Pilani

# Theme
Innovate to help cities better predict and prepare for natural disasters.

# Challenge
Earthquake is one of the most devastating natural calamities that takes thousands of lives and leaves millions more homeless and deprives them of the basic necessities. A survey from United Stated Geological Survey group shows that the last decade experienced approximately 450,000 deaths due to earthquakes. It breaks the backbone of the economy of a nation. This threat cannot be averted by mankind, but if predicted, the damage can be minimized. So, we need a tool to accurately predict earthquakes and to make unaware people aware of the basic steps and precautions that need to be taken during earthquakes.

# Solution
Natural Time (“NT”) refers to the concept of using small earthquake counts, for example of M>3 events, to mark the intervals between large earthquakes, for example M>6 events. It is particularly useful in describing complex stochastic nonlinear systems characterized by fat-tail statistics rather than Gaussian normal statistics.
We introduce the idea of nowcasting, a term originating from economics and finance. The goal of nowcasting is to determine the current state of the fault system, or put another way, the current state of progress through the earthquake cycle. 
Nowcasting enables us to forecast higher magnitude earthquakes by taking into account the number of smaller tremors in the same region. The technique used for this purpose are Long Short Term Memory(LSTM) Neural Networks. They are special kind of networks which have ability to use the previous input to predict the next value. These networks are perfectly suited for tasks involving time series analysis. Since our data, used for now casting is similar to time series, we decided to use this network.
We will be using Azure ML Lab and Azure computing services.

**Features**
1. One Click Probability of earthquake using Realtime Location
Using the **map api** and our **machine learning alogorithm** we will get a realtime location of the user. After this when User click "Predict" button of the app, using the data, our algorithm will predict the probability of earthquake at that region on the given day. In case **internet facility is not present** at the location our app will automatically **send the location and time to our server** and server will **text the probability of earthquake** to the user. This will help us to tackle the slow/No Internet problem.

2. Hospitals nearby and emergency contacts
Our app will have a section that will show the **hospital nearby** and their contacts and one click call **emergency contacts** which user can use in case of emergency

3. Gamification of general guidelines
Most of the death in earthquakes occurs due to the **lack of awareness** among people. To make people aware of the guidelines and safety measures during earthquake we will make a **fun game** through which user can learn them in a fun way. 

4. Eathquake Alert
We will send real time notification update through **Azure push messaging** Service to all the nearby areas when earthquake has striken. We can also send **text message** with the help of government to all the people nearby.

# Technology Used
Machine learning
Game Development
Java
App Development
Azure Cloud Services

# References
To develop this idea we have taken references from the following research papers

Rundle, J. B., D. L. Turcotte, A. Donnellan, L. Grant Ludwig, M. Luginbuhl, and G. Gong (2016), Nowcasting earthquakes, Earth and Space Science, 3, 480–486, doi:10.1002/2016EA000185.

Wang Q., Guo Y., Yu L. , Pan L., (2017) Earthquake Prediction based on Spatio-Temporal Data Mining: An LSTM Network Approach, Transactions on Emerging Topics in Computing, 2168-6750 















