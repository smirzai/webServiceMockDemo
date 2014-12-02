Simple Demo project for the usage of mockito to mock a Webservice implemenation.

See http://techblog.saeidmirzaei.com/?p=213



Here we are going to test LogicalOperation class. This class has one function for doing logical and.
It relies on  third party web service with Interface given in EvaluatorServiceInterface.
Instead of implementing a real fixed web service, third party web service is mocked.

During the test, we check if the truth table holds, and more important, if web service is called correctly
Specially important is that, if the first arguement is false, the second one should not be evaluated.  
