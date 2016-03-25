/*
 * Copyright (c) 2015, dhis2
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.hisp.dhis.android.dashboard.api.models;


import org.joda.time.DateTime;

import java.util.Comparator;

public interface IdentifiableObject {
    Comparator<IdentifiableObject> DISPLAY_NAME_COMPARATOR = new NameComparator();
    Comparator<IdentifiableObject> CREATED_COMPARATOR = new CreatedComparator();

    long getId();

    void setId(long id);

    String getUId();

    void setUId(String uId);

    String getName();

    void setName(String name);

    String getDisplayName();

    void setDisplayName(String displayName);

    DateTime getCreated();

    void setCreated(DateTime created);

    DateTime getLastUpdated();

    void setLastUpdated(DateTime lastUpdated);

    Access getAccess();

    void setAccess(Access access);

    class NameComparator implements Comparator<IdentifiableObject> {

        @Override
        public int compare(IdentifiableObject first, IdentifiableObject second) {
            if (first != null && first.getDisplayName() != null
                    && second != null && second.getDisplayName() != null) {

                if(!Character.isLetter(first.getDisplayName().charAt(0))||!Character.isLetter(second.getDisplayName().charAt(0))){
                    if(!Character.isDigit(first.getDisplayName().charAt(0))){
                        return 1;
                    }

                    return 1 ;
                }

                return first.getDisplayName().compareToIgnoreCase(second.getDisplayName());
            }

            return 0;
        }
    }

    class CreatedComparator implements Comparator<IdentifiableObject> {

        @Override
        public int compare(IdentifiableObject first, IdentifiableObject second) {
            if (first != null && first.getCreated() != null
                    && second != null && second.getCreated() != null) {
                if (first.getCreated().isAfter(second.getCreated())) {
                    return 1;
                }

                if (second.getCreated().isAfter(first.getCreated())) {
                    return -1;
                }
            }
            return 0;
        }
    }
}
