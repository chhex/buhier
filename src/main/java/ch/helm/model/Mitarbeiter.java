package ch.helm.model;

public class Mitarbeiter {

    private String vorName;
    private String nachHame;
    private Integer lohn;
    private OrgEinheit orgEinheit;

    public Mitarbeiter(String vorName, String nachHame) {
        this.vorName = vorName;
        this.nachHame = nachHame;
    }

    public OrgEinheit getOrgEinheit() {
        return orgEinheit;
    }

    public void setOrgEinheit(OrgEinheit orgEinheit) {
        this.orgEinheit = orgEinheit;
    }

    public String getVorName() {
        return vorName;
    }

    public void setVorName(String vorName) {
        this.vorName = vorName;
    }

    public String getNachHame() {
        return nachHame;
    }

    public void setNachHame(String nachHame) {
        this.nachHame = nachHame;
    }

    public Integer getLohn() {
        return lohn;
    }

    public void setLohn(Integer lohn) {
        this.lohn = lohn;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vorName == null) ? 0 : vorName.hashCode());
        result = prime * result + ((nachHame == null) ? 0 : nachHame.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Mitarbeiter other = (Mitarbeiter) obj;
        if (vorName == null) {
            if (other.vorName != null)
                return false;
        } else if (!vorName.equals(other.vorName))
            return false;
        if (nachHame == null) {
            return other.nachHame == null;
        } else return nachHame.equals(other.nachHame);
    }

    @Override
    public String toString() {
        return "Mitarbeiter [vorName=" + vorName + ", nachHame=" + nachHame + "]";
    }


}
