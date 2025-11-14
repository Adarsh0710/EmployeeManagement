class Developer extends Employee {
    private int projects;

    public Developer(int id, String name, double baseSalary, int projects) {
        super(id, name, baseSalary);
        this.projects = projects;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + (projects * 1000);
    }

    @Override
    public String getRole() {
        return "Developer";
    }

    public int getProjects() {
        return projects;
    }
}
