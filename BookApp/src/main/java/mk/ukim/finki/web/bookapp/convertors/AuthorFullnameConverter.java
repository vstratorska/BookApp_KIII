package mk.ukim.finki.web.bookapp.convertors;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mk.ukim.finki.web.bookapp.model.AuthorFullname;


import static javax.swing.plaf.synth.Region.SEPARATOR;

@Converter
public class AuthorFullnameConverter implements AttributeConverter<AuthorFullname, String> {

    @Override
    public String convertToDatabaseColumn(AuthorFullname fullname) {
        if (fullname == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (fullname.getSurname() != null && !fullname.getSurname()
                .isEmpty()) {
            sb.append(fullname.getSurname());
            sb.append(SEPARATOR);
        }

        if (fullname.getName() != null
                && !fullname.getName().isEmpty()) {
            sb.append(fullname.getName());
        }

        return sb.toString();

    }

    @Override
    public AuthorFullname convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }

        String[] pieces = s.split(String.valueOf(SEPARATOR));

        if (pieces == null || pieces.length == 0) {
            return null;
        }

       AuthorFullname fullname = new AuthorFullname();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (s.contains((CharSequence) SEPARATOR)) {
            fullname.setSurname(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null
                    && !pieces[1].isEmpty()) {
                fullname.setName(pieces[1]);
            }
        } else {
            fullname.setName(firstPiece);
        }

        return fullname;
    }

}

