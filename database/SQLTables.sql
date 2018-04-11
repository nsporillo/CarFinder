-- Option
-- All fields not null
CREATE TABLE IF NOT EXISTS Option (
  OptionID     INT PRIMARY KEY,
  Color        VARCHAR(255) NOT NULL,
  Engine       VARCHAR(2)   NOT NULL,
  Transmission VARCHAR(255) NOT NULL
);
-- Model
-- Brand and BodyStyle must be defined
CREATE TABLE IF NOT EXISTS Model (
  ModelID   INT AUTO_INCREMENT PRIMARY KEY,
  BrandName VARCHAR(255) NOT NULL,
  BodyStyle VARCHAR(255) NOT NULL,
);
-- Dealer
-- Require all fields not null, because Dealer Locating works best with all fields not null
CREATE TABLE IF NOT EXISTS Dealer (
  DealerID INT PRIMARY KEY,
  Name     VARCHAR(255) NOT NULL,
  Street   VARCHAR(255) NOT NULL,
  City     VARCHAR(255) NOT NULL,
  State    VARCHAR(255) NOT NULL,
  ZIP      INT          NOT NULL,
  Phone    VARCHAR(255) NOT NULL
);
-- Customer
-- No requirement for fields to be not null
CREATE TABLE IF NOT EXISTS Customer (
  CustomerID INT PRIMARY KEY,
  FirstName  VARCHAR(255),
  LastName   VARCHAR(255),
  Street     VARCHAR(255),
  City       VARCHAR(255),
  State      VARCHAR(255),
  ZIP        INT,
  Phone      VARCHAR(255),
  Gender     INT,
  Income     INT
);
-- Vehicle
-- Everything but a SaleID must be not null
CREATE TABLE IF NOT EXISTS Vehicle (
  VIN      VARCHAR(17) PRIMARY KEY,
  ModelID  INT    NOT NULL,
  OptionID INT    NOT NULL,
  SaleID   INT,
  Year     INT    NOT NULL,
  Price    DOUBLE NOT NULL,
  FOREIGN KEY (ModelID) REFERENCES Model (ModelID),
  FOREIGN KEY (OptionID) REFERENCES Option (OptionID),
);
-- Sale
-- Represents a Vehicle sale from a Customer to a Dealer
CREATE TABLE IF NOT EXISTS Sale (
  SaleID     INT AUTO_INCREMENT PRIMARY KEY,
  DealerID   INT         NOT NULL,
  CustomerID INT         NOT NULL,
  Date       TIMESTAMP,
  VIN        VARCHAR(17) NOT NULL,
  FOREIGN KEY (VIN) REFERENCES Vehicle (VIN),
  FOREIGN KEY (DealerID) REFERENCES Dealer (DealerID),
  FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID)
);
-- Add index for selecting all sales for a dealer
CREATE INDEX IF NOT EXISTS SaleIndex
  ON Sale (DealerID);
-- DealerInventory
-- A one to one mapping of a DealerID to VIN
CREATE TABLE IF NOT EXISTS DealerInventory (
  VIN      VARCHAR(17) PRIMARY KEY,
  DealerID INT NOT NULL,
  FOREIGN KEY (VIN) REFERENCES Vehicle (VIN),
  FOREIGN KEY (DealerID) REFERENCES Dealer (DealerID)
);
-- Adds an index on the DealerID column in DealerInventory for faster selection
CREATE INDEX IF NOT EXISTS DealerIndex
  ON DealerInventory (DealerID);