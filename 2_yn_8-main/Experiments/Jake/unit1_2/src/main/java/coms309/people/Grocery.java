package coms309.people;

class Grocery
{
    private String item;
    private String expirationDate;
    private String address;
    private String price;

    public Grocery()
    {
    }

    public Grocery(String item, String expirationDate, String price)
    {
        this.item = item;
        this.expirationDate = expirationDate;
        this.price = price;
    }

    public String getItem()
    {
        return this.item;
    }

    public void setItem(String item)
    {
        this.item = item;
    }

    public String getExpirationDate()
    {
        return this.expirationDate;
    }

    public void setExpirationDate(String expirationDate)
    {
        this.expirationDate = expirationDate;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String phoneNumber)
    {
        this.price = phoneNumber;
    }

    @Override
    public String toString()
    {
        return item + " " + expirationDate + " " + address + " " + price;
    }
}
