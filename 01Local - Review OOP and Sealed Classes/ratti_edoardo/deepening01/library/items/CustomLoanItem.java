package deepening01.library.items;

public non-sealed class CustomLoanItem extends LibraryItem {

    public CustomLoanItem(String title) {
        super(title);
    }

    @Override
    public int loanDays() {
        return 14;
    }
}
