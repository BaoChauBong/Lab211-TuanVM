
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
 private String id;
    private String name;
    private String location;
    private double price;
    private Date expiryDate;
    private Date dateOfManufacture;
    private String category;
    private Storekeeper storekeeper;
    private Date receiptDate;

    public Product(String id, String name, String location, double price, Date expiryDate, Date dateOfManufacture, String category, Storekeeper storekeeper, Date receiptDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.expiryDate = expiryDate;
        this.dateOfManufacture = dateOfManufacture;
        this.category = category;
        this.storekeeper = storekeeper;
        this.receiptDate = receiptDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(Date dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Storekeeper getStorekeeper() {
        return storekeeper;
    }

    public void setStorekeeper(Storekeeper storekeeper) {
        this.storekeeper = storekeeper;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

   public void display() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("%5s %10s %10s %10s %15s %25s %15s %15s %15s\n",
                id, name, location, price,
                sdf.format(expiryDate), sdf.format(dateOfManufacture), category,
                getStorekeeper().getName(), sdf.format(receiptDate));
    }

 

}
