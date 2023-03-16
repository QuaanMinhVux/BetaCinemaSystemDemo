package com.example.filmproject.models;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "sysdiagrams", schema = "dbo", catalog = "movie_webdb")
public class SysdiagramsEntity {
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "principal_id")
    private int principalId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "diagram_id")
    private int diagramId;
    @Basic
    @Column(name = "version")
    private Integer version;
    @Basic
    @Column(name = "definition")
    private byte[] definition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(int principalId) {
        this.principalId = principalId;
    }

    public int getDiagramId() {
        return diagramId;
    }

    public void setDiagramId(int diagramId) {
        this.diagramId = diagramId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public byte[] getDefinition() {
        return definition;
    }

    public void setDefinition(byte[] definition) {
        this.definition = definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysdiagramsEntity that = (SysdiagramsEntity) o;
        return principalId == that.principalId && diagramId == that.diagramId && Objects.equals(name, that.name) && Objects.equals(version, that.version) && Arrays.equals(definition, that.definition);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, principalId, diagramId, version);
        result = 31 * result + Arrays.hashCode(definition);
        return result;
    }
}
