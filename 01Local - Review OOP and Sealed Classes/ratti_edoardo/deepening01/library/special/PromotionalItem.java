package deepening01.library.special;

import deepening01.library.items.CustomLoanItem;

public class PromotionalItem extends CustomLoanItem {

    public PromotionalItem(String title) {
        super(title);
    }

    @Override
    public int loanDays() {
        return 3;
    }
}
