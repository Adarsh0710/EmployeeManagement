class HR extends Employee {
    private int recruits;

    public HR(int id, String name, double baseSalary, int recruits) {
        super(id, name, baseSalary);
        this.recruits = recruits;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + (recruits * 500);
    }

    @Override
    public String getRole() {
        return "HR";
    }

    public int getRecruits() {
        return recruits;
    }
}
