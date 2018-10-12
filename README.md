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
**Prediction part**
Artificial Neural Networks are increasingly used in predicting and classifying tasks because of their ability to capture complex relationship of output with the set of inputs. For this particular task, there are so many factors involved in the process that other model based approach do not fit the data points as accurately as neural networks do.

1. A suitable dataset will be looked for which will predict the time of occurrence, location and magnitude of earthquakes with highest accuracy. 
2. After finding the set of inputs, experiments will be conducted to find optimal set of hyper-parameters such as the number of layers, the number of neurons in each layer and various other attributes like loss function and activation functions of different layers. 
3. After obtaining the optimal set of inputs and hyper-parameters, another model will be created that forecasts the time of occurrence of earthquake using now-casting techniques. Now-casting is a technique to find the time of occurrence of large earthquakes using the count of small tremors that occur between two large earthquakes. The definition of large magnitude earthquakes changes throughout the study. We use different threshold magnitude in different experiments to consider an earthquake as large magnitude. For instance, in the starting experiments we used the threshold of magnitude 5 on Richter scale that is earthquakes having threshold 5 were considered large. Later on, threshold was changed to magnitude 6 on Richter scale. 
We will be using LSTM technique to manifest the now-casting.

The neural network models developed can prove beneficial to the community, because it can be used to create an early-warning alarm system so that the loss is minimized. The following project hope to achieve this.


**App Features**
- Hospitals nearby you using location and Maps.
- Probability of earthquake using Realtime Location.
- General Guidelines
We all are pretty much aware that if all the people are aware of the measures to be taken during the calamity then adversity can be minimized by leaps and bounds. But sadly due to lack of education  in rural areas in India, only handful of people know about these. So we would be working on spreading awareness. What we plan to do is make it like a very simple but engaging game. With different levels. For eg :The user will be put in a earthquake situation and has an option to either go to a very vacant area, a place under a big tree, a place surrounding building,  or remain in home, we can then make them rate it and according to the response will be given “quake points”. So in this way we break the monotonousity of reading the guidline pages, though they would be there for quick reference.
-Alert when earthquake has striken: We will send real time notification update through Azure push messaging Service to all the nearby areas 
- For tackling the low/no internet problem in rural areas:
They will feed in their present location and based on it we can find their approximate latitude. Cam send them monthly text updates on the probability of earthquake, or when the earthquake probability is higher than a threshold limit or when they press “Predict” button. 













**WE WENT TO PILANI VILLAGE AND THIS IS WHAT WE GOT**


![Survey](../master/Images/Survey.jpg)

 
Based on this we look to tackle the problem like the following.
Localisation and Multi-lingual Support:
The application will be implemented with multi-lingual support. For each implementation, there will be localisation for specialised earthquake related terminology used by the residents of the villages. 
Ease of Use:
Ease of use was our major focus while designing this application.
•	Keep it simple, focused: We tried to understand different activities in and during the earthquake, and based on those use cases, created focus in the application, instead of going by one interface for all flows, like a dashboard.
•	Attention on Iconography: We will be experimenting with iconography using various styles and even with contextual icons. 
We followed these simple rules of iconography: 
- Keep it simple and schematic, i.e. avoid details
- 5 second rule, if you can’t think of that icon in five seconds then that icon probably is not a good choice. Will be checking on it with local village people in Pilani.
- Memorability, making the icons distinct enough that they are remembered even after prolonged usage.
  

![Emergency](../master/Images/emergency.jpg)

**Emergency Contacts**


![Hospital](../master/Images/hospital.jpg)

**Hospital Contacts**


We are looking forward to two types of feedback. After the implementation of the application, the locals here will be offered to use it. At this stage will be able to get quick feedback about usability and other challenges.
The second feedback will have a rather longer loop, where the people will be using it in the field and on their return we will learn about how it went and get more qualitative feedback. 



