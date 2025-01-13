import java.util.*;

class Book {
    int id;
    String title;
    String author;
    boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }
}

class Member {
    int id;
    String name;
    Date membershipExpiry;

    public Member(int id, String name, Date membershipExpiry) {
        this.id = id;
        this.name = name;
        this.membershipExpiry = membershipExpiry;
    }
}

class Transaction {
    int memberId;
    int bookId;
    Date issueDate;
    Date returnDate;

    public Transaction(int memberId, int bookId, Date issueDate) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.issueDate = issueDate;
    }
}

public class LibraryManagementSystems {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public void addBook(int id, String title, String author) {
        books.add(new Book(id, title, author));
        System.out.println("Book added successfully.");
    }

    public void addMember(int id, String name, Date membershipExpiry) {
        members.add(new Member(id, name, membershipExpiry));
        System.out.println("Member added successfully.");
    }

    public void issueBook(int bookId, int memberId) {
        Optional<Book> bookOpt = books.stream().filter(b -> b.id == bookId && b.isAvailable).findFirst();
        Optional<Member> memberOpt = members.stream().filter(m -> m.id == memberId).findFirst();

        if (bookOpt.isPresent() && memberOpt.isPresent()) {
            Book book = bookOpt.get();
            book.isAvailable = false;
            transactions.add(new Transaction(memberId, bookId, new Date()));
            System.out.println("Book issued successfully.");
        } else {
            System.out.println("Book or Member not found, or Book is not available.");
        }
    }

    public void returnBook(int bookId, int memberId) {
        Optional<Transaction> transactionOpt = transactions.stream()
                .filter(t -> t.bookId == bookId && t.memberId == memberId && t.returnDate == null)
                .findFirst();

        if (transactionOpt.isPresent()) {
            Transaction transaction = transactionOpt.get();
            transaction.returnDate = new Date();
            books.stream().filter(b -> b.id == bookId).forEach(b -> b.isAvailable = true);

            long diffInMillies = Math.abs(transaction.returnDate.getTime() - transaction.issueDate.getTime());
            long diffInDays = diffInMillies / (1000 * 60 * 60 * 24);
            int fine = diffInDays > 7 ? (int) ((diffInDays - 7) * 5) : 0;

            System.out.println("Book returned successfully.");
            if (fine > 0) {
                System.out.println("Late return fine: Rs. " + fine);
            }
        } else {
            System.out.println("Transaction not found.");
        }
    }

    public void updateBook(int bookId, String newTitle, String newAuthor) {
        books.stream().filter(b -> b.id == bookId).forEach(b -> {
            b.title = newTitle;
            b.author = newAuthor;
        });
        System.out.println("Book updated successfully.");
    }

    public void updateMember(int memberId, String newName, Date newExpiry) {
        members.stream().filter(m -> m.id == memberId).forEach(m -> {
            m.name = newName;
            m.membershipExpiry = newExpiry;
        });
        System.out.println("Member updated successfully.");
    }

    public static void main(String[] args) {
        LibraryManagementSystem lms = new LibraryManagementSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Update Book");
            System.out.println("6. Update Member");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = sc.nextLine();
                    lms.addBook(bookId, title, author);
                    break;
                case 2:
                    System.out.print("Enter Member ID: ");
                    int memberId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter Member Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Membership Expiry Date (yyyy-mm-dd): ");
                    String dateStr = sc.nextLine();
                    Date expiryDate = java.sql.Date.valueOf(dateStr);
                    lms.addMember(memberId, name, expiryDate);
                    break;
                case 3:
                    System.out.print("Enter Book ID to Issue: ");
                    int issueBookId = sc.nextInt();
                    System.out.print("Enter Member ID: ");
                    int issueMemberId = sc.nextInt();
                    lms.issueBook(issueBookId, issueMemberId);
                    break;
                case 4:
                    System.out.print("Enter Book ID to Return: ");
                    int returnBookId = sc.nextInt();
                    System.out.print("Enter Member ID: ");
                    int returnMemberId = sc.nextInt();
                    lms.returnBook(returnBookId, returnMemberId);
                    break;
                case 5:
                    System.out.print("Enter Book ID to Update: ");
                    int updateBookId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter New Title: ");
                    String newTitle = sc.nextLine();
                    System.out.print("Enter New Author: ");
                    String newAuthor = sc.nextLine();
                    lms.updateBook(updateBookId, newTitle, newAuthor);
                    break;
                case 6:
                    System.out.print("Enter Member ID to Update: ");
                    int updateMemberId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Membership Expiry Date (yyyy-mm-dd): ");
                    String newDateStr = sc.nextLine();
                    Date newExpiryDate = java.sql.Date.valueOf(newDateStr);
                    lms.updateMember(updateMemberId, newName, newExpiryDate);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
