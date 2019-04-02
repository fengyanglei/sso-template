
package com.fyl.sso.model;

import lombok.Data;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apereo.cas.authentication.UsernamePasswordCredential;

@Data
public class CustomCredential extends UsernamePasswordCredential {

    private static final long serialVersionUID = -4166149641561667276L;

//    @Size(min = 1, message = "require email")
    private String email;


//    @Size(min = 1, message = "require telephone")
    private String telephone;

    public CustomCredential() {
    }

    public CustomCredential(final String email, final String telephone) {
        this.email = email;
        this.telephone = telephone;
    }


    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CustomCredential)) {
            return false;
        } else {
            CustomCredential other = (CustomCredential) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$email = this.email;
                Object other$email = other.email;
                if (this$email == null) {
                    if (other$email != null) {
                        return false;
                    }
                } else if (!this$email.equals(other$email)) {
                    return false;
                }

                Object this$telephone = this.telephone;
                Object other$telephone = other.telephone;
                if (this$telephone == null) {
                    if (other$telephone != null) {
                        return false;
                    }
                } else if (!this$telephone.equals(other$telephone)) {
                    return false;
                }

                return true;
            }
        }
    }


    protected boolean canEqual(final Object other) {
        return other instanceof CustomCredential;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(this.email)
                .append(this.telephone)
                .toHashCode();
    }


}
