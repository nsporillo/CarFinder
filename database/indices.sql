-- Adds an index on the DealerID column in DealerInventory for faster selection
CREATE INDEX IF NOT EXISTS DealerIndex ON DealerInventory(DealerID);
-- Add index for selecting all sales for a dealer 
CREATE INDEX IF NOT EXISTS SaleIndex ON Sale(DealerID)