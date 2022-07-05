# Residential Security Desk

The goal of this application is to facilitate the job of the Front Desk Security Guard or Concierge of a Residential Complex.

The typical responsibilities of such a person are:
- Access Control
- Receive Packages on behalf of the residents
- Notify residents of guests, foor deliveries or packages
- Control Keys
- Rent out a guest suite
- Keep daily log or reports
- Keep a daily water chemistry log of a pool or hot tub
- Control access to visitor parkings
- Keep a record of bicycles in bicycle storage
- Place an elevator on independant service for contractors / movers
- Accept payments for various services or deposits

Much of these duties produce paper work.  This application aims to transfer much of this paperwork in 
electronic format and store it in a database.  This application should also have the ability to export it's data 
in various formats, first and foremost in PDF format but also excel or similar spreadsheet format.

The architecture of this application calls for the use of Java 9 modules, create a module for each area of responsibility
of the application:
- CORE (business logic)
- GUI (for the user interface, in this instance Swing)
- PERSISTENCE (To save the data)
- PDFRenderer (To create PDF copies of the reports created)
- BOOTSTRAP (a tiny module whose sole purpose is to assemble the other modules and start the application,
              If at one point in time one wishes to create a different solution for GUI based on JavaFX for example
              One needs only create the new GUI Module and a new Boostrap Module to run the application with the new 
              GUI)
              
This project is the author's first big coding project, it is ment to be used in a production environment and to be a Portefolio project
for seeking future employment in the Computer Engineering or Programming industry.
